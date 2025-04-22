package accohunt;

class User{
    String email;
    String name;
    private String username;
    private String password;

    User() {
        email="";
        name="";
        username="";
        password="";
    }

    public User(String email, String name, String username, String password) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public void signUp(String email, String name, String username, String password) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public void login(String username, String password) throws AccoException{
        if(username.equals(this.username) && password.equals(this.password)){
            System.out.println("Login Successfull....");
        }
        else{
            throw new AccoException("Incorrect Username or Password !!!");
        }
    }

    public String[] getDetails(){
        String[] details = {this.name, this.email, this.username};
        return details;
    }
};





