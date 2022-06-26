import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from './model/user';
import { UserService } from './service/user.service';

@Component({
  selector: 'app-root',
  template: `
  <app-header></app-header>

  <router-outlet></router-outlet>

  <app-footer></app-footer>
  `,
  styles: []
})
export class AppComponent {
  title = 'rightrxapp';


}
