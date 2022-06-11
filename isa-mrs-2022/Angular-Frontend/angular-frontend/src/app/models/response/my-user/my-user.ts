import { Role } from "../role/role";

export class MyUserDTO {
    id: number;

    email: String;

    username: String;

    password: String;

    firstName: String;

    lastName: String;

    userCategory: String;

    roles: Role[];

    point: number;

    penaltyPoint: number;

    phoneNumber: String;

    adresa: String;

    grad: String;

    drzava: String;
}