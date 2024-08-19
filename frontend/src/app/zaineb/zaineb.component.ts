import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-zaineb',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './zaineb.component.html',
  styleUrl: './zaineb.component.css'
})
export class ZainebComponent {

  profilePhotoUrl: string = '/assets/img/.jpg'; 

  constructor(private router: Router) {}

  onFileChange(event: Event): void {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files.length > 0) {
      const file = fileInput.files[0];
      const reader = new FileReader();
      reader.onload = () => {
        this.profilePhotoUrl = reader.result as string; 
      };
      reader.readAsDataURL(file); 
    }
  }

  logout() {
    // Clear all local storage
    localStorage.clear();

    // Redirect to the home page
    this.router.navigate(['/']);
  }
}
