import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import * as constants from "../constants";
import {Observable, throwError} from "rxjs";
import {Customer} from "../common/customer";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {
  }

  customerId = '';

  private scanCardUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/tryOn/scanCard';
  private logoutUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/tryOn/logout';
  private isloggedinUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/tryOn/isloggedin';
  private registerUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/customer/save';
  private updateUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/customer/update';
  private getCustomerUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/customer/get/email';

  postScanCard(requestParams: HttpParams): Observable<string> {
    console.log(this.scanCardUrl);
    return this.http.get<string>(this.scanCardUrl, {params: requestParams});
  }

  logout(): Observable<any> {
    console.log(this.logoutUrl);
    return this.http.get<any>(this.logoutUrl);
  }

  isloggedin(): Observable<any> {
    console.log(this.isloggedinUrl);
    return this.http.get<any>(this.isloggedinUrl);
  }

  register(jsonBody: string, requestParams: HttpParams): Observable<any> {
    console.log(this.registerUrl);
    console.log(jsonBody);
    return this.http.post<any>(this.registerUrl, {jsonBody}, {params: requestParams});
  }

  getCustomer(requestParams: HttpParams): Observable<Customer> {
    return this.http.get<Customer>(this.getCustomerUrl, {params: requestParams});
  }

  handleError;

  postUserInput(userInput: Customer) {

    const headers = {'Content-Type': 'application/json'};
    const body = JSON.stringify(userInput);

    return this.http.post(this.registerUrl, body, {"headers": headers}).pipe(
      catchError(err => {
        return throwError(err);
      })
    )
      .toPromise();
    // await this.http.post(this.registerUrl, body, {"headers": headers}).toPromise().catch((err: HttpErrorResponse) => {
    //   // simple logging, but you can do a lot more, see below
    //   console.error('An error occurred:', err.error.message);
    //   this.handleError = err.error; //.message;
    // }).then(
    //   resp => {
    //     resp = Response;
    //     return resp;
    //
    //   }
    // ).catch(this.handleError);
    // throw this.handleError;
    // return this.handleError;
  }

  updateUserInput(userInput: Customer) {

    const headers = {'Content-Type': 'application/json'};
    const body = JSON.stringify(userInput);

    return this.http.post(this.updateUrl, body, {"headers": headers}).pipe(
      catchError(err => {
        return throwError(err);
      })
    )
      .toPromise();
  }

}
