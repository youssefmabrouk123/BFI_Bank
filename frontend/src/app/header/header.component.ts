import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  headerScrolled = false;

  @HostListener('window:scroll', [])
  onWindowScroll() {
    console.log('Scroll event detected');
    this.headerScrolled = window.pageYOffset > 50; // Adjust the scroll value as needed
    console.log('Header scrolled:', this.headerScrolled);
  }
}