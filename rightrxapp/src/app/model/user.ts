export class User{
    
    public id: number|null = null;
    public username: String;
    public password: String;
    public role: String;
    public authorities: [];

    constructor() {
        this.username = '';
        this.password = '';
        this.role = '';
        this.authorities = [];
    }
}