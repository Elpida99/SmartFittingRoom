import {Component, OnInit} from '@angular/core';
import {BreakpointObserver} from "@angular/cdk/layout";
import {Router} from "@angular/router";
import {CustomerService} from "../../services/customer.service";
import {DomSanitizer} from "@angular/platform-browser";
import {MatDialog} from "@angular/material/dialog";
import {PurchaseService} from "../../services/purchase.service";
import {HttpParams} from "@angular/common/http";
import {PurchaseJpo} from "../../common/purchase-jpo";

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {

  constructor(private breakpointObserver: BreakpointObserver,
              private router: Router,
              private purchaseService: PurchaseService,
              private service: CustomerService,
              private sanitizer: DomSanitizer,
              private dialog: MatDialog) {
    this.service.isloggedin().subscribe(data => {
      console.log(data);
      this.loggedin = data['loggedIn'];
      this.customer = data['email'];
      console.log(this.loggedin);
      console.log(this.customer);
    });

  }

  loggedin;
  customer;
  pos = [];

  ngOnInit(): void {
     this.getMyPurchases();
  }

  getMyPurchases() {
    this.pos = [];
    console.log("My Purchases")
    let requestParams = new HttpParams();
    requestParams = requestParams.append('customer', 'papadkon@gmail.com');
    this.purchaseService.getByCustomer(requestParams).subscribe(data => {
        console.log(data);
       // this.pos = data;
       this.pos.push(data);
      console.log("pos[0]");
      console.log(this.pos[0]);
      console.log("pos");
      console.log(this.pos);
    });
  }


  logout() {
    this.service.logout().subscribe(data => {
      console.log(data);
    });

    this.goToPage('');
  }


  goToPage(pageName: string) {
    console.log(pageName);
    this.router.navigate([`smartFittingRoom/${pageName}`]);
  }

}
