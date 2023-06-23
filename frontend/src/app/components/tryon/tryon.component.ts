import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HttpParams} from "@angular/common/http";
import {GarmentService} from "../../services/garment.service";
import {DomSanitizer} from "@angular/platform-browser";
import {Observable} from "rxjs";
import {map, shareReplay} from "rxjs/operators";
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Garment} from "../../common/garment";
import {Material} from "../../common/material";
import {StoreAvailabilityJpo} from "../../common/store-availability";
import {MatTableDataSource} from "@angular/material/table";
import {SizeComponent} from "../size/size.component";
import {MatDialog} from "@angular/material/dialog";
import {CustomerService} from "../../services/customer.service";


@Component({
  selector: 'app-tryon',
  templateUrl: './tryon.component.html',
  styleUrls: ['./tryon.component.css']
})
export class TryonComponent implements OnInit {

  constructor(private breakpointObserver: BreakpointObserver,
              private router: Router,
              private garmentService: GarmentService,
              private service: CustomerService,
              private sanitizer: DomSanitizer,
              private dialog: MatDialog) {
    this.service.isloggedin().subscribe(data => {
      console.log(data);
      this.loggedin = data['loggedIn'];
    });
  }

  ngOnInit(): void {
  }

  loggedin;
  barcode = "";
  garment: Garment;//[];
  garmentFound = false;
  availClicked = false;
  storeAvailability: StoreAvailabilityJpo [];
  outfits: Garment[];
  material: Material[];
  dataSource = new MatTableDataSource(this.storeAvailability);
  displayedColumns: string[] = ["STORE", "SIZE", "COLOUR", "QUANTITY"];
  colours = [];
  sizes = [];
  discount;

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

  imageUrl;

  getImage(data) {
    let objectURL = 'data:image/png;base64,' + data;
    this.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectURL);
  }

  getDiscount(gar:Garment) {
    console.log("discount");
    let disc = gar.price * (1 - gar.garmentDetails.discountPercentage);
    console.log(disc);
    return  Math.floor(disc * 100) / 100;
  }

  findGarment(sku) {
    console.log("findGarment");
    let requestParams = new HttpParams();
    requestParams = requestParams.append('sku', sku);
    this.garmentService.getGarmentBySku(requestParams).subscribe(data => {
      console.log(data);
      this.barcode=data[0].barcode;
      this.searchBarcode();
    });
  }

  searchBarcode() {
    this.material = [];
    this.outfits = [];
    this.availClicked = false;
    this.garment = new Garment();
    console.log("In search");
    console.log(this.barcode);


    let requestParams = new HttpParams();
    requestParams = requestParams.append('barcode', this.barcode);
    this.garmentService.postscanGarment(requestParams).subscribe(data => {
      console.log(data);
    });


    this.garmentService.getGarmentByBarcode(requestParams).subscribe(data => {

      console.log("response");
      console.log(data);
      this.garment = data;
      if (data != null) {
        this.garmentFound = true;
        console.log(this.garmentFound);
        this.discount = this.garment.price * (1 - this.garment.garmentDetails.discountPercentage);
        this.discount = Math.floor(this.discount * 100) / 100;

        let matrequestParams = new HttpParams();
        console.log("SKU");
        console.log(this.garment.skuNumber);
        matrequestParams = matrequestParams.append('garmentCode', this.garment.skuNumber);
        this.garmentService.getMaterialBlend(matrequestParams).subscribe(data => {
          this.material = data;
        });

        //get available colours
        let coloursrequestParams = new HttpParams();
        console.log("SKU");
        console.log(this.garment.skuNumber);
        coloursrequestParams = coloursrequestParams.append('garmentCode', this.garment.skuNumber);
        this.garmentService.getGarmentColours(coloursrequestParams).subscribe(data => {
          this.colours = data;
        });

        //get available sizes
        let sizesrequestParams = new HttpParams();
        console.log("SKU");
        console.log(this.garment.skuNumber);
        sizesrequestParams = sizesrequestParams.append('garmentCode', this.garment.skuNumber);
        this.garmentService.getGarmentSizes(sizesrequestParams).subscribe(data => {
          this.sizes = data;
        });

        this.getRecommendation();
      }
    });


    // this.garmentService.getGarmentByBarcode(requestParams)
    //   .subscribe((data: Garment) => this.garment = {
    //     //this.garment: data
    //     // tmpgarment:data
    //     garment:data
    //   });

    console.log("found: " + this.garmentFound);
    console.log("garment: " + this.garment);


  }

  openDialog() {
    const dialogRef = this.dialog.open(SizeComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  seeAvailability() {
    this.storeAvailability = [];
    this.availClicked = true;
    let requestParams = new HttpParams();
    requestParams = requestParams.append('barcode', this.barcode);
    this.garmentService.getAvailability(requestParams).subscribe(data => {
      console.log("response");
      console.log(data);
      this.storeAvailability = data;
      this.dataSource = new MatTableDataSource(this.storeAvailability);
    });
  }

  getRecommendation() {
    this.outfits = [];
    let requestParams = new HttpParams();
    requestParams = requestParams.append('sku', this.garment.skuNumber)
    this.garmentService.getRecommendation(requestParams).subscribe(data => {
      this.outfits = data;
    });
  }


  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

}
