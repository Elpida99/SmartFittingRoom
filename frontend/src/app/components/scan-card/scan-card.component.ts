import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpParams} from "@angular/common/http";
import {CustomerService} from "../../services/customer.service";


@Component({
  selector: 'app-scan-card',
  templateUrl: './scan-card.component.html',
  styleUrls: ['./scan-card.component.css']
})
export class ScanCardComponent implements OnInit {


  constructor(private router: Router, private http: HttpClient, private service: CustomerService) {
    this.service.isloggedin().subscribe(data => {
      console.log(data);
      this.loggedin = data['loggedIn'];
    });
  }

  cardId = "";
  username = "";
  handleError;
  errorThrown = false;
  loggedin;

  logout() {
    this.service.logout().subscribe(data => {
      console.log(data);
    });

    this.goToPage('');
  }

  myTryons() {
    this.handleError = null;
    this.errorThrown = false;
    console.log("Scan card: ");
    console.log(this.cardId);

    let requestParams = new HttpParams();
    requestParams = requestParams.append('customerId', this.cardId);
    this.service.postScanCard(requestParams).subscribe(data => {
      console.log("response");
      console.log(data);
      this.username = data;
      if (data != null) {
        console.log(this.username)
        this.goToPage('/' + this.username['username'] + '/myTryons');
      }
    }, error => {
      console.log(error);
      this.handleError = error;
      this.errorThrown = true;
    });

    if (this.handleError != null) {
      this.errorThrown = true;
    }
  }

  scanCard() {
    this.handleError = null;
    this.errorThrown = false;
    console.log("Scan card: ");
    console.log(this.cardId);

    let requestParams = new HttpParams();
    requestParams = requestParams.append('customerId', this.cardId);

    this.service.postScanCard(requestParams).subscribe(data => {
      console.log("response");
      console.log(data);
      this.username = data;
      if (data != null) {
        console.log(this.username)
        this.goToPage('/' + this.username['username'] + '/tryon');
      }
    }, error => {
      console.log(error);
      this.handleError = error;
      this.errorThrown = true;
    });


    if (this.handleError != null) {
      this.errorThrown = true;
    }

  }


  ngOnInit(): void {
    // this.service.isloggedin().subscribe(data => {
    //   this.loggedin = data;
    // });
  }

  goToPage(pageName: string) {
    console.log("IN GO TO PAGE");
    this.router.navigate([`smartFittingRoom/${pageName}`]);
  }

}
