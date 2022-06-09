import { Role } from "../role/role";

export class MyUserDTO {
    id: number;

    email: String;

    username: String;

    password: String;

    firstName: String;

    lastName: String;

    roles: Role[];

    point: number;

    phoneNumber: String;

    adresa: String;

    grad: String;

    drzava: String;
}