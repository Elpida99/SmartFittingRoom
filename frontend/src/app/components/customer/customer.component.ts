import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient, HttpParams} from "@angular/common/http";
import {CustomerService} from "../../services/customer.service";
import {Address} from "../../common/address";
import {Customer} from "../../common/customer";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient, private service: CustomerService) {
    this.isloggedin();
  }

  loggedin;
  email;

  isloggedin() {
    this.service.isloggedin().subscribe(data => {
      console.log(data);
      this.loggedin = data['loggedIn'];
      this.email = data['email'];
      console.log(this.loggedin);

      if(this.loggedin!='none') {
        this.getCustomer();
      }
    });

  }

  cardId = "";
  username = "";
  handleError;
  errorThrown = false;

  ngOnInit(): void {
   // this.getCustomer();
  }

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

  getCustomer() {

    console.log('email');
    console.log(this.email);
    let requestParams = new HttpParams();
    requestParams = requestParams.append('email', this.loggedin + '@gmail.com');

    this.service.getCustomer(requestParams).subscribe(data => {
      console.log(data);
      this.customerResult = data;
      console.log(this.customerResult);
    });
  }

  async update() {
    this.handleError = null;
    this.errorThrown = false;
    this.address = new Address(this.customerResult.address.street, this.customerResult.address.number, this.customerResult.address.city, this.customerResult.address.postCode);
    this.customer = new Customer(this.customerResult.firstName, this.customerResult.lastName, this.customerResult.phoneNumber, this.customerResult.email, this.address);

    const body = JSON.stringify(this.customer);
    let requestParams = new HttpParams();
    requestParams = requestParams.append('Content-Type', 'application/json');

    try {
      await this.service.updateUserInput(this.customer);
    } catch (ex) {
      this.handleError = ex;
      console.log("HANDLE")
      console.log(this.handleError)
    }

    if (this.handleError != null) {
      this.errorThrown = true;
    }

    if (this.errorThrown == false) {
      this.goToPage('');
    }


  }

}
