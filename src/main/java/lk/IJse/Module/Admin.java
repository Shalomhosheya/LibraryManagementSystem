package lk.IJse.Module;

import jakarta.persistence.*;

@Entity
public class Admin {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use an appropriate strategy for your database
//    @Column(name = "id") // Specify the column name if it's different from the field name
    private String id;

    private String name;
    private String password;
    private String email;


    public Admin() {
    }

    public String getId() {
        return id;
    }

    public String setId(String id) {
        this.id = id;
        return id;
    }

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String setPassword(String password) {
        this.password = password;
        return password;
    }
}
