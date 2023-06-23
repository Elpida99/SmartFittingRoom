import {MaterialModule} from './material/material.module';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http'
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {ClipboardModule} from '@angular/cdk/clipboard';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import { GarmentComponent } from './components/garment/garment.component';
import { SizeComponent } from './components/size/size.component';
import {HomeComponent} from "./components/home/home.component";
import {MatRadioModule} from "@angular/material/radio";
import { CustomerComponent } from './components/customer/customer.component';
import { ScanCardComponent } from './components/scan-card/scan-card.component';
import { TryonComponent } from './components/tryon/tryon.component';
import { CategoryComponent } from './components/category/category.component';
import { RegisterComponent } from './components/register/register.component';
import { MytryonComponent } from './components/mytryon/mytryon.component';
import { AdminComponent } from './components/admin/admin.component';
import { PurchaseComponent } from './components/purchase/purchase.component';


const routes: Routes = [
  {path: 'smartFittingRoom', component: HomeComponent},
  {path: 'smartFittingRoom/customer', component: CustomerComponent},
  {path: 'smartFittingRoom/scanCard', component: ScanCardComponent},
  {path: 'smartFittingRoom/register', component: RegisterComponent},
  // {path: 'tryon', component: TryonComponent},
  {path: 'smartFittingRoom/tryon', component: TryonComponent},
  {path: 'smartFittingRoom/admin', component: AdminComponent},
  {path: 'smartFittingRoom/garments', component: GarmentComponent},
  {path: '', pathMatch: 'full', redirectTo: 'smartFittingRoom'},
  {
    path: 'smartFittingRoom',
    children: [
      {
        path: ':name', //FOR VARIABLE VALUE
        children: [
          {
            path: 'tryon', //REMOVE SLASH
            component: TryonComponent
          }
        ]
      },
      {
        path: ':name', //FOR VARIABLE VALUE
        children: [
          {
            path: 'category', //REMOVE SLASH
            component: CategoryComponent
          }
        ]
      },
      {
        path: ':name', //FOR VARIABLE VALUE
        children: [
          {
            path: 'garments', //REMOVE SLASH
            component: GarmentComponent
          }
        ]
      },
      {
        path: ':name', //FOR VARIABLE VALUE
        children: [
          {
            path: 'myTryons', //REMOVE SLASH
            component: MytryonComponent
          },
          {
            path: 'myPurchases', //REMOVE SLASH
            component: PurchaseComponent
          },
          {
            path: 'myProfile', //REMOVE SLASH
            component: CustomerComponent
          }
        ]
      }
    ]
  }
];

@NgModule({
  declarations: [
    AppComponent,
    GarmentComponent,
    SizeComponent,
    HomeComponent,
    CustomerComponent,
    ScanCardComponent,
    TryonComponent,
    CategoryComponent,
    RegisterComponent,
    MytryonComponent,
    AdminComponent,
    PurchaseComponent

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    RouterModule.forRoot(routes),
    FormsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    ClipboardModule,
    ReactiveFormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatRadioModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
