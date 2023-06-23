import { Component } from '@angular/core';
// import { LoginService } from './services/login.service';
// import { HttpClient } from '@angular/common/http';
// import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'smart-fitting-room';

  // constructor(private loginService: LoginService, private http: HttpClient, private router: Router) {
  //   this.loginService.authenticate(undefined, undefined);
  // }
  // logout() {
  //   // @ts-ignore
  //   this.http.post('logout', {}).finally(() => {
  //     this.loginService.authenticated = false;
  //     this.router.navigateByUrl('/login');
  //   }).subscribe();
  // }
}
