import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ZainebComponent } from '../zaineb/zaineb.component';

interface RendezVous {
  id: number;
  date: string;
  idClient: number;
  lienMeet: string;
  done: boolean;
}

@Component({
  selector: 'app-rdv',
  standalone: true,
  imports: [CommonModule, FormsModule, ZainebComponent],
  templateUrl: './rdv.component.html',
  styleUrls: ['./rdv.component.css']
})
export class RdvComponent implements OnInit {
  rendezvousList: RendezVous[] = [];
  filteredRendezvousList: RendezVous[] = [];
  apiUrl = 'http://localhost:8999/api/v1/Account/rendezvous';

  dateFilter: string = '';
  statusFilter: string = '';
  idFilter: string = '';

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getAllRendezVous();
  }

  getAllRendezVous() {
    this.http.get<RendezVous[]>(`${this.apiUrl}/all`).subscribe(
      (data) => {
        this.rendezvousList = data;
        this.filteredRendezvousList = data; // Initialize filtered list
      },
      (error) => {
        console.error('Error fetching rendez-vous data', error);
      }
    );
  }

  markAsDone(id: number) {
    this.http.post(`${this.apiUrl}/setDone/${id}`, {}).subscribe(
      () => {
        // Update status in the list
        const rdv = this.rendezvousList.find(r => r.id === id);
        if (rdv) {
          rdv.done = true;
          this.applyFilters(); // Reapply filters to update the display
        }
      },
      (error) => {
        console.error('Error marking rendez-vous as done', error);
      }
    );
  }

  applyFilters() {
    this.filteredRendezvousList = this.rendezvousList.filter(rdv => {
      const dateMatch = this.dateFilter ? this.filterByDate(rdv.date, this.dateFilter) : true;
      const statusMatch = this.statusFilter ? rdv.done.toString() === this.statusFilter : true;
      const idMatch = this.idFilter ? rdv.id.toString().includes(this.idFilter) : true;
      return dateMatch && statusMatch && idMatch;
    });
  }

  filterByDate(rdvDate: string, filterDate: string): boolean {
    const rdvDateObj = new Date(rdvDate);
    const filterDateObj = new Date(filterDate);
    return rdvDateObj.toDateString() === filterDateObj.toDateString();
  }
}
