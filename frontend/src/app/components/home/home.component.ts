import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Component} from '@angular/core';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {Router} from '@angular/router';
import {CustomerService} from "../../services/customer.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {


  constructor(private breakpointObserver: BreakpointObserver, private router: Router, private service: CustomerService) {
    this.isloggedin();
  }

  loggedin;

  isloggedin() {
    this.service.isloggedin().subscribe(data => {
      console.log(data);
      this.loggedin = data['loggedIn'];
      console.log(this.loggedin);
    });
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  option = "Continue as guest";
  options: string[] = ['Scan your member card', 'Continue as guest', 'Register', 'Login as Admin'];

  customerSelect() {
    console.log(this.option);
    if(this.option == "Scan your member card") {
      this.logout();
      this.goToPage('scanCard');
    } else if(this.option == "Continue as guest") {
      this.logout();
      this.goToPage('tryon');
    } else if(this.option == "Register") {
      this.goToPage('register');
    } else if(this.option == "Login as Admin") {
      this.logout();
      this.goToPage('admin');
    }


  }

  logout() {
    this.service.logout().subscribe(data => {
      console.log('logout');
      console.log(data);
    });

    this.isloggedin();

    this.goToPage('');
  }

  goToPage(pageName: string) {
    this.router.navigate([`smartFittingRoom/${pageName}`]);
  }
}
