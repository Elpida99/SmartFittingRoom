import {Component, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {Router} from "@angular/router";
import {GarmentService} from "../../services/garment.service";
import {CustomerService} from "../../services/customer.service";
import {DomSanitizer} from "@angular/platform-browser";
import {MatDialog} from "@angular/material/dialog";
import {Garment} from "../../common/garment";
import {StoreAvailabilityJpo} from "../../common/store-availability";
import {Material} from "../../common/material";
import {MatTableDataSource} from "@angular/material/table";
import {HttpParams} from "@angular/common/http";
import {SizeComponent} from "../size/size.component";
import {Observable} from "rxjs";
import {map, shareReplay} from "rxjs/operators";

@Component({
    selector: 'app-mytryon',
    templateUrl: './mytryon.component.html',
    styleUrls: ['./mytryon.component.css']
})
export class MytryonComponent implements OnInit {

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
        this.getMyTryOns();
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
    tryons = []

    getMyTryOns() {
        this.tryons = [];
        console.log("My tryons")
        this.garmentService.getmyTryons().subscribe(data => {
            console.log(data);
           this.tryons = data;
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

    imageUrl;

    getImage(data) {
        let objectURL = 'data:image/png;base64,' + data;
        this.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectURL);
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
        this.garmentService.getGarmentByBarcode(requestParams).subscribe(data => {
            console.log("response");
            console.log(data);
            this.garment = data;
            if (data != null) {
                this.garmentFound = true;
                console.log(this.garmentFound);

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

                // this.getRecommendation();
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

    // seeAvailability() {
    //     this.storeAvailability = [];
    //     this.availClicked = true;
    //     let requestParams = new HttpParams();
    //     requestParams = requestParams.append('barcode', this.barcode);
    //     this.garmentService.getAvailability(requestParams).subscribe(data => {
    //         console.log("response");
    //         console.log(data);
    //         this.storeAvailability = data;
    //         this.dataSource = new MatTableDataSource(this.storeAvailability);
    //     });
    // }
    //
    // getRecommendation() {
    //     this.outfits = [];
    //     let requestParams = new HttpParams();
    //     requestParams = requestParams.append('sku', this.garment.skuNumber)
    //     this.garmentService.getRecommendation(requestParams).subscribe(data => {
    //         this.outfits = data;
    //     });
    // }


    isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
        .pipe(
            map(result => result.matches),
            shareReplay()
        );


}
