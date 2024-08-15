import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-zaineb',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './zaineb.component.html',
  styleUrl: './zaineb.component.css'
})
export class ZainebComponent {

  profilePhotoUrl: string = '/assets/img/.jpg'; 
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
}
