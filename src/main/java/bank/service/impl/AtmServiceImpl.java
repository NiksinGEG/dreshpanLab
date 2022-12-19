package main.java.bank.service.impl;

import main.java.bank.base.BankRepository;
import main.java.bank.entity.BankAtm;
import main.java.bank.entity.BankOffice;
import main.java.bank.entity.Employee;
import main.java.bank.entity.enums.AtmStatuses;
import main.java.bank.exceptions.NotFoundException;
import main.java.bank.service.AtmService;

import java.util.Collection;

public class AtmServiceImpl implements AtmService{
    private BankRepository rep;
    public AtmServiceImpl(BankRepository rep)
    {
        this.rep = rep;
    }
    @Override
    public BankAtm getAtm(int id) throws NotFoundException {
        var res = rep.atms.get(id);
        if(res == null) throw new NotFoundException(id, BankAtm.class);
        return res;

    }
    @Override
    public Collection<BankAtm> getAllAtms() {
        return rep.atms.get();
    }
    @Override
    public BankAtm addAtm(BankAtm atm) {
        try
        {
            rep.atms.add(atm);
            return atm;
        }
        catch(Exception ex)
        {
            System.out.println("Произошла ошибка при добавлении нового банкомата" + ex.getMessage());
            return null;
        }
    }
    @Override
    public BankAtm updateAtm(BankAtm model) {
        try {
            return rep.atms.update(model);
        }
        catch (Exception ex) {
            System.out.println("Ошибка при изменении банкомата");
            return null;
        }
    }
    @Override
    public void placeToOffice(BankAtm atm, BankOffice office) {
        if(!office.canSetAtm) return;
        try {
            atm.placingOffice = office;
            atm.adress = office.address;
            office.countAtm++;
            rep.atms.update(atm);
            rep.offices.update(office);
        }
        catch(Exception ex) {
            System.out.println("Произошла ошибка при помещении банкомата в офис");
        }
    }
    @Override
    public BankAtm setEmployee(int atmId, Employee employee) throws RuntimeException {
        BankAtm atm = rep.atms.get(atmId);
        atm.employee = employee;
        return rep.atms.update(atm);
    }
    @Override
    public double takeMoney(int atmId, double amount) {
        BankAtm atm = rep.atms.get(atmId);
        double res = 0;
        if(atm.status == AtmStatuses.noMoney || atm.status == AtmStatuses.notWorking)
            return res;
        res = Math.min(amount, atm.getMoneyCount());
        atm.setMoneyCount(atm.getMoneyCount() - amount);
        var totalAmount = atm.bank.totalMoneyCount - amount;
        if(totalAmount < 0)
            atm.bank.totalMoneyCount = 0;
        else
            atm.bank.totalMoneyCount = totalAmount;
        return res;
    }
    @Override
    public double depositMoney(int atmId, double amount) {
        BankAtm atm = rep.atms.get(atmId);
        atm.setMoneyCount(atm.getMoneyCount() + amount);
        atm.bank.totalMoneyCount += amount;
        return amount;
    }
}
