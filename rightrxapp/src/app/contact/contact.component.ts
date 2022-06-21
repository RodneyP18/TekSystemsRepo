import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  name: string | undefined;
  email: string | undefined;
  message: string | undefined;
  
  constructor() { }

  ngOnInit(): void {
  }

  submitForm(){
    alert('Form Submitted');
  }
}
