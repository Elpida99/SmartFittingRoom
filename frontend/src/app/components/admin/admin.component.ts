import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {CustomerService} from "../../services/customer.service";
import {Observable} from "rxjs";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {map, shareReplay} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";
import * as constants from "../../constants";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {

  constructor(private breakpointObserver: BreakpointObserver, private router: Router, private service: CustomerService, private http: HttpClient) {
  }


  download() {
    const fileUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/admin/download';

    this.http.get(fileUrl, {responseType: 'blob'}).subscribe((response: Blob) => {
      const downloadLink = document.createElement('a');
      downloadLink.href = URL.createObjectURL(response);
      downloadLink.download = 'data.xlsx';
      downloadLink.click();
    });
  }

  logout() {
    this.service.logout().subscribe(data => {
      console.log('logout');
      console.log(data);
    });

    this.goToPage('admin');
  }

  goToPage(pageName: string) {
    this.router.navigate([`smartFittingRoom/${pageName}`]);
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

}
