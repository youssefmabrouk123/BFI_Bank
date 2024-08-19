import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-youssef',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './youssef.component.html',
  styleUrl: './youssef.component.css'
})
export class YoussefComponent {



  profilePhotoUrl: string | ArrayBuffer | null = '/path/to/default/profile-photo.jpg';

  constructor(private router: Router) {}


onFileChange(event: Event): void {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files.length > 0) {
        const file = fileInput.files[0];
        const reader = new FileReader();
        reader.onload = () => {
            this.profilePhotoUrl = reader.result;
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
