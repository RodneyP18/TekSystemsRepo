import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Review } from '../model/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {


  private host = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }


  public getReviews(): Observable<Review[] | HttpErrorResponse>{
    return this.http.get<Review[]>(`${this.host}/review/getReviews`);
  }

  public addReview(formData: FormData): Observable<Review | HttpErrorResponse>{
    return this.http.put<Review>(`${this.host}/review/add`, formData);
  }


  public getReviewsFromLocalCache(): Review[]{
    if(localStorage.getItem('reviews')){
      return JSON.parse(localStorage.getItem('reviews') || '');
    }
    return null as any;
  }

  public getReviewFromLocalCache(): Review{
    if(localStorage.getItem('review')){
      return JSON.parse(localStorage.getItem('review') || '');
    }
    return null as any;
  }

  public addReviewsToLocalCache(review: Review[]): void{
    localStorage.setItem('review', JSON.stringify(review));
  }

}
