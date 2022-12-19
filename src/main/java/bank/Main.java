import main.java.bank.Startup;
import main.java.bank.base.BankRepository;
import main.java.bank.base.EntityContainer;
import main.java.bank.entity.*;
import main.java.bank.service.*;
import main.java.bank.service.impl.*;

import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static void printMenu(Collection<Bank> banks){
        System.out.println("Пожалуйста, выберите нужный банк для разбора:");
        for(var bank : banks){
            System.out.println(bank.id + ": " + bank.name);
        }
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
            System.out.println("===Неиного информации о вас: Кредитный рейтинг: " + choosenUser.creditRate + "===");
            //System.out.println(st.bankService.get(bankId));
        }
        catch(Exception ex){
            System.out.println("Что то пошло не так: " + ex.getMessage());
        }
    }

}