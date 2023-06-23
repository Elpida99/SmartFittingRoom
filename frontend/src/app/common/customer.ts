import {Address} from "./address";

export class Customer {

  firstName: string;
  lastName: string;
  phoneNumber: string;
  email: string;
  address: Address;

  constructor(firstName, lastName, phoneNumber, email, address){
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;
  }


}
