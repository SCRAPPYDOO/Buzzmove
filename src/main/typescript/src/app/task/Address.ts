export class Address {
    public id: number;

    constructor(public adressLine: string, public adressSecondLine: string, public town: string, public county: string, public postcode: string) {}
    
    public toString() {
        return this.adressLine + " " + (this.adressSecondLine == "" ? "" : " " + this.adressSecondLine) + " " + this.town + " " + this.county + " " + this.postcode;
    }
}