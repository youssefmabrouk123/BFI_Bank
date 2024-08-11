import { Component, OnInit } from '@angular/core';
import { YoussefComponent } from '../youssef/youssef.component';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-edit',
  standalone: true,
  imports: [YoussefComponent],
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.css'
})
export class EditComponent implements OnInit {
  user: any = {}; // Variable to hold user details

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.getUserDetails().subscribe(userDetails => {
      this.user = userDetails;
      console.log(this.user) ;
    });
  }
}