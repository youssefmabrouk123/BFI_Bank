import { Component , OnInit} from '@angular/core';
import { AuthService } from '../auth.service';
import { ZainebComponent } from '../zaineb/zaineb.component';

@Component({
  selector: 'app-liste-demandes',
  standalone: true,
  imports: [ZainebComponent],
  templateUrl: './liste-demandes.component.html',
  styleUrl: './liste-demandes.component.css'
})
export class ListeDemandesComponent implements OnInit {
  user: any = {}; // Variable to hold user details

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.getUserDetails().subscribe(userDetails => {
      this.user = userDetails;
      console.log(this.user) ;
    });
  }

}
