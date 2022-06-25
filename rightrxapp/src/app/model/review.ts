import { Drug } from "./drug";
import { User } from "./user";

export class Review{
    public id: number|null = null;
    public drug: Drug = new Drug;
    public user: User = new User;

    constructor() {
        this.drug = this.drug;
        this.user = this.user;

    }
}
