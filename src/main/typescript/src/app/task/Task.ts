import { Address } from "./Address";

export class Task {
    constructor(public id: number, public title: string, public firstName: string, public lastName: string, public scheduledDate: Date, public address: Address, public notes: string) {
    }
}