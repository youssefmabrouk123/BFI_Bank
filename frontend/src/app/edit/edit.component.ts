import { Component, OnInit, Renderer2 } from '@angular/core';
import { YoussefComponent } from '../youssef/youssef.component';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit',
  standalone: true,
  imports: [YoussefComponent,CommonModule],
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  user: any = {}; // Variable to hold user details
  profilePhotoUrl: string | ArrayBuffer | null = null; // Variable to hold the profile photo URL

  constructor(private authService: AuthService, private renderer: Renderer2) {}

  ngOnInit(): void {
    this.authService.getUserDetails().subscribe(userDetails => {
      if (userDetails) {
        this.user = userDetails;
       // this.loadProfilePhoto();
      } else {
        console.log('User details not yet available.');
      }
    });
  }

  // loadProfilePhoto(): void {
  //   if (this.user.id) {
  //     this.authService.getProfilePhoto(this.user.id).subscribe(photoBlob => {
  //       const reader = new FileReader();
  //       reader.onload = () => {
  //         this.profilePhotoUrl = reader.result;
  //       };
  //       reader.readAsDataURL(photoBlob);
  //     });
  //   }
  // }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.profilePhotoUrl = reader.result;
      };
      reader.readAsDataURL(file);
      
      this.authService.uploadProfilePhoto(this.user.id, file).subscribe(() => {
        console.log('Profile photo uploaded successfully.');
      });
    }
  }

  triggerFileInput(): void {
    const fileInput = this.renderer.selectRootElement('#fileInput', true);
    fileInput.click();
  }
}
