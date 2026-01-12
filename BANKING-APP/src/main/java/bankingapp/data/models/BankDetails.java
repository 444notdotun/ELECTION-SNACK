package bankingapp.data.models;

import lombok.Getter;

@Getter
public enum BankDetails {
    FIRSTBANK("011"),
    ACCESSBANK("044"),
    FCMB("214"),
    UBA("033"),
    WEMABANK("035"),
    UNIONBANK("032"),
    ZENITH("057"),
    STERLING("232");

    private String bankcode;
    BankDetails(String bankCode) {
        this.bankcode = bankCode;
    }

}
