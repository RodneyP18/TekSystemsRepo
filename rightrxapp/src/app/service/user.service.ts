import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CustomHttpResponse } from '../model/custom-http-response';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private host = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }


  public getUser(): Observable<User | HttpErrorResponse>{
    return this.http.get<User>(`${this.host}/user/getUser`);
  }

  public registerUser(formData: FormData): Observable<User | HttpErrorResponse>{
    return this.http.post<User>(`${this.host}/user/register`, formData);
  }

  public updateUser(formData: FormData): Observable<User | HttpErrorResponse>{
    return this.http.put<User>(`${this.host}/user/update`, formData);
  }

  public deleteUser(userId: number): Observable<CustomHttpResponse | HttpErrorResponse>{
    return this.http.delete<CustomHttpResponse>(`${this.host}/user/delete/${userId}`);
  }

  public addUserToLocalCache(user: User): void{
    localStorage.setItem('user', JSON.stringify(user));
  }

  public getUserFromLocalCache(): User{
    if(localStorage.getItem('user')){
      return JSON.parse(localStorage.getItem('user') || '');
    }
    return null as any;
  }

  public createUserFormData(loggedInUsername: string, user: User): FormData {
    const formData = new FormData();
    formData.append('currentUsername', loggedInUsername);
    formData.append('password', user.password.toString())
    return formData;
  }
}
