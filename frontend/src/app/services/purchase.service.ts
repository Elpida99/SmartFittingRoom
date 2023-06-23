import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import * as constants from "../constants";
import {Observable} from "rxjs";
import {PurchaseJpo} from "../common/purchase-jpo";

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  constructor(private http: HttpClient) {
  }


  private getByCustomerUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/purchase/get/byCustomer';

  getByCustomer(requestParams: HttpParams): Observable<PurchaseJpo> {
    return this.http.get<PurchaseJpo>(this.getByCustomerUrl, {params: requestParams});
  }
}
