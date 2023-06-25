import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient, HttpParams} from "@angular/common/http";
import {CustomerService} from "../../services/customer.service";
import {Customer} from "../../common/customer";
import {Address} from "../../common/address";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient, private service: CustomerService) {
  }

  cardId = "";
  username = "";
  handleError;
  errorThrown = false;
  created = false;

  ngOnInit(): void {
  }

  firstName;
  lastName;
  phoneNumber;
  email;
  street;
  number;
  city;
  postCode;
  address;
  customer;
  customerResult;


  logout() {
    this.service.logout().subscribe(data => {
      console.log(data);
    });

    this.goToPage('');
  }

  goToPage(pageName: string) {
    console.log("IN GO TO PAGE");
    this.router.navigate([`smartFittingRoom/${pageName}`]);
  }

  async register() {
    this.handleError = null;
    this.errorThrown = false;
    this.created = false;
    this.address = new Address(this.street, this.number, this.city, this.postCode);
    this.customer = new Customer(this.firstName, this.lastName, this.phoneNumber, this.email, this.address);

    const body = JSON.stringify(this.customer);
    let requestParams = new HttpParams();
    requestParams = requestParams.append('Content-Type', 'application/json');

    try {
      this.customerResult = await this.service.postUserInput(this.customer);
      if(this.customerResult!=null) {
        this.created = true;
      }
    } catch (ex) {
      this.handleError = ex;
      console.log("HANDLE")
      console.log(this.handleError)
    }

    if (this.handleError != null) {
      this.errorThrown = true;
    }

    if (this.errorThrown == false) {
      this.created = true;
    //  this.goToPage('');
    }


  }
}
