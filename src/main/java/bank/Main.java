import main.java.bank.Startup;
import main.java.bank.StringHelper;
import main.java.bank.base.BankRepository;
import main.java.bank.base.BaseEntity;
import main.java.bank.base.EntityContainer;
import main.java.bank.entity.*;
import main.java.bank.entity.enums.BankTransaction;
import main.java.bank.exceptions.BankTransactionException;
import main.java.bank.exceptions.ValidationException;
import main.java.bank.service.*;
import main.java.bank.service.impl.*;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static <T extends BaseEntity> T printMenu(Collection<T> entityes){
        System.out.println("===================Выберите нужное=======================");
        System.out.println(StringHelper.fromCollectionToString(entityes));
        System.out.print("Выберите нужную сущность: "); Scanner sc = new Scanner(System.in);
        var choosenId = sc.nextInt();
        Optional<T> entity = entityes.stream()
                .filter(x -> x.id == choosenId)
                .findFirst();
        System.out.println("==========================================================");
        return entity.get();
    }
    public static void main(String[] args) throws Exception{
        try{
            Startup st = new Startup();
            st.initBanks();
            //printMenu(st.bankService.getAll());

            System.out.print("Добрый вечер, введите свой уникальный идентификационнй номер: ");
            Scanner scanner = new Scanner(System.in);
            var userId = scanner.nextInt();
            var choosenUser = st.userService.getUser(userId);
            System.out.println("===Ващ кредитный рейтинг: " + choosenUser.creditRate);
            System.out.print("===Введите сумму, которую хотели взять в кредит: ");
            var sum = scanner.nextDouble();
            var creditAcc = getCredit(choosenUser, st, sum);
            if(creditAcc != null)
            {
                System.out.println("===Поздравляем, вам выдан кредит на сумму: "+sum);
                System.out.println(creditAcc.toString());
            }
            else{
                System.out.println("===К сожалению пока мы не можем выдать вам кредит. Попробуйте позже");
            }
        }
        catch(Exception ex){
            System.out.println("Что то пошло не так: " + ex.getMessage());
        }

    }
    private static CreditAccount getCredit(User user, Startup st, double creditSum) throws Exception{
        var bank = printMenu(st.bankService.getAll());
        System.out.println("===Выберите нужный офис");
        var offices = bank.offices.stream()
                .filter(x -> x.isWorking == true &&
                        x.isGiveCredits == true)
                .toList();
        if(offices.size() == 0) throw new Exception("Работающих и выдающих кредиты офисов нет");
        var office = printMenu(offices);
        var employees = office.employees.stream()
                .filter(x -> x.canGiveCredit == true)
                .toList();
        if(employees.size() == 0) throw new Exception("Выдающих кредиты содрудиков нет");
        var employee = printMenu(employees);
        var paymentAccount = user.paymentAccounts.stream()
                .filter(x -> x.bankName == bank.name)
                .findFirst()
                .orElse(st.paService.openPaymentAccount(user.id, bank.id, 0));
        CreditValidation(user, bank);
        var atm = office.bankAtms.stream()
                .filter(x -> x.isGiveMoney == true &&
                        x.getMoneyCount() >= creditSum)
                .findFirst()
                .orElse(null);
        if(atm != null) {
            double moneyAmount = st.atmService.takeMoney(atm.id, creditSum);
            if(moneyAmount != creditSum){
                throw new BankTransactionException("Непредвиденная ошибка получения денег банкомата", atm.id, BankTransaction.Distursement);
            }
            return st.caService.openCreditAccount(user.id, bank.id, employee.id, paymentAccount.id, moneyAmount / 12, 12);
        }
        else{
            BankOffice officee = null;
            for (var off:
                    offices) {
                var atmOptional = off.bankAtms.stream()
                        .filter(x -> x.isGiveMoney == true &&
                                x.getMoneyCount() >= creditSum)
                        .findFirst();
                if(atmOptional.isPresent())
                    officee = off;
            }
            if(officee != null){
                System.out.println("Обратитесь в офис по адресу " + officee.address);
                return null;
            }
        }
        return null;
    }

    private static void CreditValidation(User user, Bank bank) throws Exception {
        if(user.creditRate < 5000 && bank.rating > 50) {
            throw new ValidationException("Пользователь с id=" + user.id, "creditRate", user.creditRate);
        }
    }
}