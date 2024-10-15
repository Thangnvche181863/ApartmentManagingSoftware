/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import model.Invoice;

/**
 *
 * @author ADMIN
 */
public class UserHomeUtil {
    public int numInvoiceInYear(List<Invoice> list, int year) {
        int count = 0;
        for (Invoice invoice : list) {
            if (invoice.getIssueDate().toLocalDate().getYear() == year) {
                count += 1;
            }
        }
        return count;
    }

    public LinkedHashSet<Integer> listOfYear(List<Date> list) {
        LinkedHashSet<Integer> yList = new LinkedHashSet<>();
        for (Date date : list) {
            yList.add(date.toLocalDate().getYear());
        }
        return yList;
    }

    public LinkedHashSet<Date> listOfMonth(List<Date> list, int year) {
        LinkedHashSet<Date> mList = new LinkedHashSet<>();
        for (Date date : list) {
            if (date.toLocalDate().getYear() == year) {
                mList.add(date);
            }
        }
        return mList;
    }

    public double totalAmount(List<Invoice> list, int year) {
        double total = 0;
        for (Invoice invoice : list) {
            if (invoice.getIssueDate().toLocalDate().getYear() == year) {
                total += invoice.getAmount();
            }
        }
        return total;
    }

    public double paidAmount(List<Invoice> list, int year) {
        double total = 0;
        for (Invoice invoice : list) {
            // check if invoice is paid or not
            // status = 1 => paid
            // status = 0 => unpaid
            if (invoice.getStatus() == 1 && invoice.getIssueDate().toLocalDate().getYear() == year) {
                total += invoice.getAmount();
            }
        }
        return total;
    }

    public double unPaidAmount(List<Invoice> list, int year) {
        double total = 0;
        for (Invoice invoice : list) {
            // check if invoice is paid or not
            // status = 1 => paid
            // status = 0 => unpaid
            if (invoice.getStatus() == 0 && invoice.getIssueDate().toLocalDate().getYear() == year) {
                total += invoice.getAmount();
            }
        }
        return total;
    }

    public List<Double> listAmountByMonth(List<Invoice> list, int year) {
        double init = 0;
        List<Double> amountList = new ArrayList<>(Arrays.asList(init, init, init, init, init, init, init, init, init, init, init, init));
        for (Invoice invoice : list) {
            if (invoice.getIssueDate().toLocalDate().getYear() == year) {
                int month = invoice.getIssueDate().toLocalDate().getMonthValue();
                amountList.add(month - 1, amountList.get(month - 1) + invoice.getAmount());
            }
        }
        return amountList;
    }
}
