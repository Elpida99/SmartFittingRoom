import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import * as constants from "../constants";
import {Observable} from "rxjs";
import {Garment} from "../common/garment";
import {Material} from "../common/material";
import {StoreAvailabilityJpo} from "../common/store-availability";
import {TryOn} from "../common/try-on";

@Injectable({
    providedIn: 'root'
})
export class GarmentService {

    constructor(private http: HttpClient) {
    }

    private getGarmentByBarcodeUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/garment/get/barcode';
    private getGarmentByNameUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/garment/get/name';
    private getGarmentBySkuUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/garment/get/sku';
    private getAllGarmentsUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/garment/get/all';
    private getMaterialBlendUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/garment/get/material';
    private getGarmentColoursUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/garment/get/colours';
    private geGarmentSizesUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/garment/get/sizes';
    private getAvailabilityUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/tryOn/availability';
    private getRecommendationUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/tryOn/outfits';
    private getmyTryonsUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/tryOn/myTryons';
    private postscanGarmentUrl = 'http://' + constants.HOST + ':' + constants.PORT + '/tryOn/scanGarment';

    postscanGarment(requestParams: HttpParams): Observable<TryOn> {
        return this.http.post<TryOn>(this.postscanGarmentUrl, null,{params: requestParams});
    }

    getGarmentByBarcode(requestParams: HttpParams): Observable<Garment> {
        return this.http.get<Garment>(this.getGarmentByBarcodeUrl, {params: requestParams});
    }

    getAvailability(requestParams: HttpParams): Observable<StoreAvailabilityJpo[]> {
        return this.http.get<StoreAvailabilityJpo[]>(this.getAvailabilityUrl, {params: requestParams});
    }

    getRecommendation(requestParams: HttpParams): Observable<Garment[]> {
        return this.http.get<Garment[]>(this.getRecommendationUrl, {params: requestParams});
    }

    getmyTryons(): Observable<TryOn[]> {
        return this.http.get<TryOn[]>(this.getmyTryonsUrl);
    }

    getGarmentByName(requestParams: HttpParams): Observable<Garment> {
        return this.http.get<Garment>(this.getGarmentByNameUrl, {params: requestParams});
    }

    getGarmentBySku(requestParams: HttpParams): Observable<Garment> {
        return this.http.get<Garment>(this.getGarmentBySkuUrl, {params: requestParams});
    }

    getAllGarments(): Observable<Garment[]> {
        return this.http.get<Garment[]>(this.getAllGarmentsUrl);
    }

    getMaterialBlend(requestParams: HttpParams): Observable<Material[]> {
        return this.http.get<Material[]>(this.getMaterialBlendUrl, {params: requestParams});
    }

    getGarmentColours(requestParams: HttpParams): Observable<string[]> {
        return this.http.get<string[]>(this.getGarmentColoursUrl, {params: requestParams});
    }

    getGarmentSizes(requestParams: HttpParams): Observable<string[]> {
        return this.http.get<string[]>(this.geGarmentSizesUrl, {params: requestParams});
    }
}


