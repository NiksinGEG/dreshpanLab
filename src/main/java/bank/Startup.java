package main.java.bank;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.*;
import main.java.bank.service.*;
import main.java.bank.service.impl.*;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class Startup {
    private final BankRepository rep = new BankRepository();
    public final AtmService atmService = new AtmServiceImpl(rep);
    public final EmployeeService employeeService = new EmployeeServiceImpl(rep);
    public final UserService userService = new UserServiceImpl(rep);
    public final BankOfficeService bankOfficeService = new BankOfficeServiceImpl(rep, employeeService);
    public final BankService bankService = new BankServiceImpl(rep, atmService, bankOfficeService, employeeService, userService);
    public final PaymentAccountService paService = new PaymentAccountServiceImpl(rep, userService, bankService);
    public final CreditAccounService caService = new CreditAccountServiceImpl(rep, userService, bankService, employeeService, paService);

    Random rnd = new Random();
    private final String[] names = new String[]{
            "Жмышенко М.П.",
            "Иванов И.И.",
            "Петров П.П.",
            "Абобович А.П",
            "Дрешпан Н.В."
    };

    private final String[] posts = new String[]{
            "Консультант","Оператор","Менеджер","Бухгалтер","Уборщица"
    };

    public void initBanks() throws RuntimeException{
        Random rnd = new Random();
        Bank bank = new Bank();
        bank.name = "FPeople";
        bank.totalMoneyCount = rnd.nextDouble(1000000);
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);
        initClients(bank);

        bank = new Bank();
        bank.name = "Тенек";
        bank.totalMoneyCount = rnd.nextDouble(1000000);
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);
        initClients(bank);

        bank = new Bank();
        bank.name = "РосГосНавозБанк";
        bank.totalMoneyCount = rnd.nextDouble(1000000);
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);
        initClients(bank);

        bank = new Bank();
        bank.name = "LamaBank";
        bank.totalMoneyCount = rnd.nextDouble(1000000);
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);
        initClients(bank);

        bank = new Bank();
        bank.name = "СпёрБанк";
        bank.totalMoneyCount = rnd.nextDouble(1000000);
        bankService.addBank(bank);
        initBankAtms(bank);
        initOffices(bank);
        initClients(bank);
    }

    void initClients(Bank bank) throws RuntimeException{
        for(int i = 0; i < 5; i++){
            User user = new User();
            user.name = names[rnd.nextInt(names.length - 1)];
            user.setSalary(rnd.nextDouble(100000));
            user.birthday = new Date();
            userService.addUser(user);
            bankService.addBankUser(bank.id, user.id);
            initPaymentAccounts(user, bank);
            initCreditAccounts(user, bank);
        }
    }
    void initEmployeer(BankOffice office){
        for(int i = 0; i < 5; i++) {
            Employee employee = new Employee();
            employee.name = names[rnd.nextInt(names.length)];
            employee.birthday = new Date();
            employee.post = posts[rnd.nextInt(names.length)];
            employee.salary = rnd.nextInt(10000);
            employee.isWorkDistance = rnd.nextInt(10) > 5;
            employee.canGiveCredit = rnd.nextInt(10) > 5;
            employeeService.addEmployee(employee);
            bankOfficeService.addEmployeeToOffice(office.id, employee.id);

            Collection<Bank> banks = bankService.getAll();
            var bank = banks.stream()
                    .filter(bank1 -> bank1.offices.contains(office))
                    .findFirst()
                    .orElse(null);
            if (bank != null)
                bankService.addEmployeeToBank(bank.id, employee.id);
        }
    }

    void initOffices(Bank bank){
        BankOffice office = new BankOffice();
        office.name = bank.name + " офис обслуживания номер 1";
        office.address = "г.Белгород, ул. Спортивная, д" + rnd.nextInt(1, 100);
        office.rentPrice = 15000;
        office.canSetAtm = true;
        bankOfficeService.addOffice(office);
        bankService.addNewBankOffice(bank.id, office.id);
        initEmployeer(office);

        office = new BankOffice();
        office.name = bank.name + " офис обслуживания номер 1";
        office.address = "c.Абобовка, ул. Жмышенко, д" + rnd.nextInt(1, 100);
        office.rentPrice = 1000;
        office.canSetAtm = false;
        bankOfficeService.addOffice(office);
        bankService.addNewBankOffice(bank.id, office.id);
        initEmployeer(office);

        office = new BankOffice();
        office.name = bank.name + " офис обслуживания номер 1";
        office.address = "г.Бибово, ул. Бобова, д" + rnd.nextInt(1, 100);
        office.rentPrice = 2000;
        office.canSetAtm = true;
        bankOfficeService.addOffice(office);
        bankService.addNewBankOffice(bank.id, office.id);
        initEmployeer(office);
    }
    void initBankAtms(Bank bank) throws RuntimeException{
        BankAtm atm = new BankAtm();
        atm.name = bank.name + " банкомат номер 1";
        atm.isGiveMoney = true;
        atm.isTakeMoney = true;
        atm.adress = "с. Хитропоповка, ул. Базарова д"+rnd.nextInt(1, 100);
        atm.setMoneyCount(rnd.nextDouble(bank.totalMoneyCount / 3));
        atm.servicePrice = rnd.nextInt(2000);
        atmService.addAtm(atm);
        bankService.addAtmToBank(bank.id, atm.id);

        atm = new BankAtm();
        atm.name = bank.name + " банкомат номер 2";
        atm.isGiveMoney = true;
        atm.isTakeMoney = false;
        atm.adress = "г.Белгород, ул. Спортивная, д"+rnd.nextInt(1, 100);
        atm.setMoneyCount(rnd.nextDouble(bank.totalMoneyCount / 3));
        atm.servicePrice = rnd.nextInt(2000);
        atmService.addAtm(atm);
        bankService.addAtmToBank(bank.id, atm.id);

        atm = new BankAtm();
        atm.name = bank.name + " банкомат номер 3";
        atm.isGiveMoney = false;
        atm.isTakeMoney = true;
        atm.adress = "г.Бибово, ул. Бобова, д"+rnd.nextInt(1, 100);
        atm.setMoneyCount(rnd.nextDouble(bank.totalMoneyCount / 3));
        atm.servicePrice = rnd.nextInt(2000);
        atmService.addAtm(atm);
        bankService.addAtmToBank(bank.id, atm.id);
    }

    void initPaymentAccounts(User user, Bank bank){
        for(int i = 0; i < 2; i++){
            paService.openPaymentAccount(user.id, bank.id, rnd.nextDouble(500));
        }
    }
    void initCreditAccounts(User user, Bank bank) throws RuntimeException{
        var creditial = bank.employees.stream()
                .filter((employee) -> employee.canGiveCredit == true)
                .findFirst()
                .orElse(null);
        if(creditial == null)
            throw new RuntimeException("Никто не выдает кредиты в банке " + bank.name);
        for(PaymentAccount paAccount : user.paymentAccounts)
            caService.openCreditAccount(user.id, bank.id, creditial.id, paAccount.id, rnd.nextDouble(10000), rnd.nextInt(12));
    }
}
