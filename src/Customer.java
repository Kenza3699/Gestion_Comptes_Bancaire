
public class Customer {
     private int Id;
     private String adresse;
     private String name;
     private String familyName;
     private double amount;
     private String passsword;

    public Customer(int Id, String adress, String name, String familyName, double amount,String password) {
        this.Id = Id;
        this.adresse = adress;
        this.name = name;
        this.familyName = familyName;
        this.amount = amount;
        this.passsword=password;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdress(String adress) {
        this.adresse = adresse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
}
