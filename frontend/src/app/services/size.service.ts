import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import * as constants from "../constants";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SizeService {

  constructor(private http : HttpClient) { }

  private getSizesUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/size/all';

  getSizeTable(): Observable<Size[]> {
    return this.http.get<Size[]>(this.getSizesUrl);
  }
}
//TODO: if garment has discount show it on the UI
interface Size {
  size: string,
  waist: number,
  bust: number,
  hips: number,
  gender: string
};
