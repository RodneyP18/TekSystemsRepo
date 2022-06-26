import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Drug } from '../model/drug';
import { Review } from '../model/review';
import { ReviewService } from '../service/review.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  public reviews: Review[] = [];
  private subscriptions: Subscription[] = [];
  private drug: Drug = new Drug;

  constructor(private reviewService: ReviewService) { }


  ngOnInit(): void {
    this.getReviews();
  }

  public getReviews(): void {
    this.subscriptions.push(
      this.reviewService.getReviews().subscribe(
        (response: Review[]) => {
          this.reviewService.addReviewsToLocalCache(response);
          this.reviews = response;
        }
      )
    );
  }

  // public searchReviews(searchTerm: string): void {
  //   const results: Review[] = [];
  //   for (const review of this.reviewService.getReviewsFromLocalCache()) {
  //     if (review.drug.toLowerCase.indexOf(searchTerm.toLowerCase()) !== -1) {
  //       results.push(review);
  //     }
  //   }
  //   this.reviews = results;
  //   if (results.length === 0 || !searchTerm) {
  //     this.reviews = this.reviewService.getReviewsFromLocalCache();
  //   }
  // }


}
