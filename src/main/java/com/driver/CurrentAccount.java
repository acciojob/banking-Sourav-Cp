package com.driver;


import java.util.HashMap;
import java.util.Map;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;

        if(balance < 5000)
        {
            throw new Exception("Insufficient Balance");
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception


        Map<Character,Integer> characterIntegerMap = new HashMap<>();

        int maxCnt = -1;

        for(int i=0;i<tradeLicenseId.length();i++)
        {
            char curr = tradeLicenseId.charAt(i);
            int val = characterIntegerMap.getOrDefault(curr,0)+1;

            if(maxCnt < val)
            {
                maxCnt = val;
            }
            characterIntegerMap.put(curr,val);
        }

        boolean validating = checkLicence(tradeLicenseId);

        if(! validating)
        {
            if(maxCnt > (tradeLicenseId.length()+1)/2) throw new Exception("Valid License can not be generated");
        }

        char[] formingId = new char[tradeLicenseId.length()];

        int index1 = 0;
        int index2 = 1;

        for(Character ch : characterIntegerMap.keySet())
        {
            int cntOfChar = characterIntegerMap.get(ch);
            while(cntOfChar > 0 && index1 < formingId.length)
            {
                formingId[index1] = ch;
                index1 += 2;
                cntOfChar--;
            }

            if(index1 >= formingId.length)
            {
                while(cntOfChar > 0 && index2 < formingId.length)
                {
                    formingId[index2] = ch;
                    index2 += 2;
                    cntOfChar--;
                }
            }
         //   characterIntegerMap.remove(ch);

        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<formingId.length;i++)
        {
            sb.append(formingId[i]);
        }
        tradeLicenseId = sb.toString();

    }
    public boolean checkLicence(String tradeLicenseId)
    {
        for(int i=0;i<tradeLicenseId.length()-1;i++)
        {
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)) return false;
        }
        return true;
    }

}
