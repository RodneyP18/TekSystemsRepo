import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../model/user';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  public user: User = new User;
  
  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn();
    this.user = this.authService.getUserFromLocalCache();
  }

  public isLoggedIn(): boolean{
    return this.authService.isUserLoggedIn();   
  }

  public logout(){
    this.authService.logout();
  }
}
