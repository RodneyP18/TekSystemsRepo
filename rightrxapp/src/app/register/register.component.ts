import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from '../model/user';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {

  private subscription: Subscription[] = [];


  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
    if (this.authenticationService.isUserLoggedIn()) {
      this.router.navigateByUrl('');
    } 
  }

  public onRegister(user: User): void {
    this.subscription.push(
      this.authenticationService.register(user).subscribe(
        (response: User) => {
          alert("New account was created for " + `${response.username}`);
        },
        (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      )
    );
  }

  ngOnDestroy(): void {
    this.subscription.forEach(sub => sub.unsubscribe());
  }
}
