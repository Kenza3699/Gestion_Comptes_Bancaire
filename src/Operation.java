
import java.sql.Date;
import java.sql.Timestamp;
import javafx.beans.property.SimpleStringProperty;






public class Operation {
    private int Id;
     private int operationNumber;
     private String type;
     private String dateOfCreation;
     private double amount;

    public Operation(int Id, int operationNumber, String type,String dateOfCreation, double amount) {
        this.Id = Id;
        this.operationNumber = operationNumber;
        this.type = type;
        this.dateOfCreation=dateOfCreation;
        this.amount = amount;
    }

    public String getdateOfCreation() {
        return this.dateOfCreation;
    }

    public void setdateOfCreation(String dateOfCreation) {
        this.dateOfCreation=dateOfCreation;
    }

  

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(int operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
     
}
