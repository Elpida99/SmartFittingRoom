import {Component} from '@angular/core';
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {HttpClient, HttpParams} from "@angular/common/http";
import {GarmentService} from "../../services/garment.service";
import {MatDialog} from "@angular/material/dialog";
import {Observable} from "rxjs";
import {map, shareReplay} from "rxjs/operators";
import {Garment} from "../../common/garment";
import {SizeComponent} from "../size/size.component";
import {DomSanitizer} from '@angular/platform-browser';
import {Router} from '@angular/router';



@Component({
  selector: 'app-garment',
  templateUrl: './garment.component.html',
  styleUrls: ['./garment.component.css']
})
export class GarmentComponent {

  constructor(private breakpointObserver: BreakpointObserver,
              private http: HttpClient,
              private garmentService: GarmentService, private dialog: MatDialog,
              private sanitizer: DomSanitizer, private router: Router) {
    this.submitSearch();
  }

  imageUrl;

  getImage(data) {
    let objectURL = 'data:image/png;base64,' + data;
    this.imageUrl = this.sanitizer.bypassSecurityTrustUrl(objectURL);
  }


  openDialog() {
    const dialogRef = this.dialog.open(SizeComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  garmentSearch = "";
  garments: Garment[];

  firstTimeDisabled = true;
  historyPanelOpened = false;


  submitSearch() {
    this.garments = [];
    console.log("In submit");
    console.log(this.garmentSearch);
    if (this.garmentSearch == null || this.garmentSearch == "") {
      this.garmentService.getAllGarments().subscribe(data => {
        this.garments = data;
      });
    } else {
      let requestParams = new HttpParams();
      requestParams = requestParams.append('sku', this.garmentSearch);
      this.garmentService.getGarmentBySku(requestParams).subscribe(data => {
        this.garments.push(data);
      });
      if (this.garments == []) {
        requestParams = requestParams.append('barcode', this.garmentSearch);
        this.garmentService.getGarmentByBarcode(requestParams).subscribe(data => {
          this.garments.push(data);
        });
      }
      if (this.garments == []) {
        requestParams = requestParams.append('name', this.garmentSearch);
        this.garmentService.getGarmentByName(requestParams).subscribe(data => {
          this.garments.push(data);
        });
      }
    }
  }

  handleError;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );


  goToPage(pageName: string) {
    this.router.navigate([`smartFittingRoom/${pageName}`]);
  }
}
