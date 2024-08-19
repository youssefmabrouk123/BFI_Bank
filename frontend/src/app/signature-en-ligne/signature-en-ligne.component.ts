  // import { Component , OnInit } from '@angular/core';
  // import jsPDF from 'jspdf';

  // import { YoussefComponent } from "../youssef/youssef.component";
  // import { HttpClient } from '@angular/common/http';
  // import { CommonModule } from '@angular/common';

  // @Component({
  //   selector: 'app-signature-en-ligne',
  //   standalone: true,
  //   imports: [ YoussefComponent ,CommonModule],
  //   templateUrl: './signature-en-ligne.component.html',
  //   styleUrls: ['./signature-en-ligne.component.css']  // Corrected here
  // })
  // export class SignatureEnLigneComponent implements OnInit {
  //   private canvas!: HTMLCanvasElement;
  //   private ctx: CanvasRenderingContext2D | null = null;
  //   signatureImageUrl: string | undefined;
  //   clientName: string = 'NON CLIENT'; 
  //   clientAddress: string = 'ADRESSE CLIENT';

  //   constructor(private http: HttpClient) {

  //   }


  //   ngOnInit(): void {
  //     this.getSignature();
  //     this.canvas = document.getElementById('signature-pad') as HTMLCanvasElement;
  //     this.ctx = this.canvas.getContext('2d');
  //     if (this.ctx) {
  //       this.initializeCanvas();
  //     }
  //   }

  //   initializeCanvas(): void {
  //     if (!this.ctx) return;

  //     this.ctx.strokeStyle = '#000';
  //     this.ctx.lineWidth = 2;
  //     this.ctx.lineCap = 'round';
  //     this.ctx.lineJoin = 'round';

  //     let isDrawing = false;

  //     this.canvas.addEventListener('mousedown', () => isDrawing = true);
  //     this.canvas.addEventListener('mouseup', () => isDrawing = false);
  //     this.canvas.addEventListener('mousemove', (event) => {
  //       if (isDrawing && this.ctx) {
  //         const rect = this.canvas.getBoundingClientRect();
  //         const x = event.clientX - rect.left;
  //         const y = event.clientY - rect.top;
  //         this.draw(x, y);
  //       }
  //     });

  //     this.canvas.addEventListener('mouseout', () => isDrawing = false);
  //   }

  //   draw(x: number, y: number): void {
  //     if (!this.ctx) return;

  //     this.ctx.lineTo(x, y);
  //     this.ctx.stroke();
  //     this.ctx.beginPath();
  //     this.ctx.moveTo(x, y);
  //   }

  //   clearSignature(): void {
  //     if (!this.ctx || !this.canvas) return;

  //     this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
  //   }

  //   // saveSignature(): void {
  //   //   if (!this.canvas) return;

  //   //   const dataURL = this.canvas.toDataURL('image/png');
  //   //   this.generatePDF(dataURL);
  //   // }

  //   getSignature() {
  //     const compteId =  localStorage.getItem("idCompte"); // Replace with the actual ID of the CompteBancaire

  //     this.http.get<{ signature: string }>(`http://localhost:8999/api/compte-bancaire/${compteId}/signature`)
  //       .subscribe(response => {
  //         this.signatureImageUrl = response.signature; // Assign the base64 string to be used as an image source
  //       }, error => {
  //         console.error('Error retrieving signature:', error);
  //       });
  //   }

  //   saveSignature() {
  //     const canvas = document.getElementById('signature-pad') as HTMLCanvasElement;
  //     const signatureDataUrl = canvas.toDataURL('image/png');

  //     const compteId = localStorage.getItem("idCompte");
  //     ; // Replace with the actual ID of the CompteBancaire

  //     this.http.post(`http://localhost:8999/api/v1/Account/comptes/${compteId}/save-signature`, { signature: signatureDataUrl })
  //       .subscribe(response => {
  //         console.log('Signature saved successfully!', response);
  //         this.generatePDF(signatureDataUrl);
  //       }, error => {
  //         console.error('Error saving signature:', error);
  //       });
  //   }

  //   generatePDF(signatureDataURL: string): void {
  //     const doc = new jsPDF();
  //     const lineHeight = 10;  // Hauteur de ligne ajustée
  //     const pageWidth = doc.internal.pageSize.width;
  //     const pageHeight = doc.internal.pageSize.height;
  //     let yOffset = 10;
    
  //     // Fonction pour ajouter le texte en le divisant en lignes si nécessaire
  //     const addText = (text: string, isHeader: boolean = false) => {
  //       doc.setFontSize(isHeader ? 20 : 12);  // Taille de police
  //       const lines = doc.splitTextToSize(text, pageWidth - 20);
  //       lines.forEach((line: string) => {
  //         if (yOffset + lineHeight > pageHeight - 60) {  // Laisser de l'espace pour la signature
  //           doc.addPage();
  //           yOffset = 10;  // Réinitialiser la position verticale
  //         }
  //         doc.text(line, 10, yOffset);
  //         yOffset += lineHeight;
  //       });
  //     };
    
  //     // Ajouter le contenu du contrat
  //     doc.setFont('helvetica', 'normal');
      
  //     // Ajouter des styles manuellement pour imiter le CSS
  //     doc.setFontSize(30);
  //     doc.text('Contrat', 10, yOffset);
  //     yOffset += 15;
    
  //     doc.setFontSize(12);
  //     addText(`Entre : BFI BANK, située à Immeuble BFI, Rue du Lac de Constance, Les Berges du Lac, 1053 Tunis, Tunisie.`);
  //     addText(`Et : ${this.clientName}, résidant à ${this.clientAddress}.`);
  //     addText('Objet du Contrat', true);
  //     addText('Le présent contrat a pour objet de définir les termes et conditions sous lesquels l\'Entreprise fournira au Client les services suivants :');
  //     addText('Service 1: Ouverture de Compte Bancaire - Assistance complète dans l\'ouverture d\'un nouveau compte bancaire, incluant la collecte et la vérification des documents nécessaires, la configuration des options de compte selon les besoins du client, et la fourniture d\'un accès sécurisé aux services bancaires en ligne.');
  //     addText('Service 2: Gestion de Compte et Transactions - Gestion quotidienne du compte bancaire, comprenant le traitement des transactions, le suivi des soldes, la gestion des virements et paiements, ainsi que la fourniture de relevés de compte réguliers. Ce service inclut également la gestion des autorisations et des alertes de sécurité.');
  //     addText('Service 3: Conseils Financiers et Assistance - Fourniture de conseils financiers personnalisés pour aider le client à optimiser la gestion de son compte bancaire. Cela comprend des recommandations sur les produits financiers adaptés, des stratégies d\'épargne et d\'investissement, ainsi qu\'une assistance en cas de problème ou de questionnement concernant le compte.');
  //     addText('Durée du Contrat', true);
  //     addText('Le présent contrat entre en vigueur à compter de la date de signature et se terminera le 20/08/2030.');
  //     addText('Obligations des Parties', true);
  //     addText('Obligations de l\'Entreprise :');
  //     addText('1. Fournir des services bancaires conformes aux termes du contrat, incluant l\'ouverture et la gestion du compte bancaire du Client.');
  //     addText('2. Assurer la confidentialité des informations financières et personnelles du Client.');
  //     addText('3. Informer le Client de tout changement concernant les frais, les conditions ou les services proposés.');
  //     addText('Obligations du Client :');
  //     addText('1. Fournir des informations exactes et complètes lors de l\'ouverture du compte bancaire.');
  //     addText('2. Respecter les termes et conditions du contrat ainsi que les règlements internes de l\'Entreprise.');
  //     addText('Confidentialité', true);
  //     addText('Les Parties s\'engagent à maintenir la confidentialité des informations échangées dans le cadre du présent contrat.');
  //     addText('Résiliation', true);
  //     addText('Le contrat peut être résilié par l\'une ou l\'autre des Parties sous réserve de respecter un préavis de 60 jours.');
  //     addText('Litiges', true);
  //     addText('En cas de litige relatif au présent contrat, les Parties s\'engagent à tenter de résoudre le différend à l\'amiable avant de saisir les tribunaux compétents.');
  //     addText('Signatures', true);
  //     const today = new Date();
  //   const formattedDate = today.toLocaleDateString('fr-FR', {
  //     day: '2-digit',
  //     month: '2-digit',
  //     year: 'numeric'
  //   });
    
  //   addText(`Fait le ${formattedDate}`);
  //   addText('Pour le Client :');
      
  //     // Ajouter la signature au PDF
  //     const signatureWidth = 50;
  //     const signatureHeight = 50;
  //     const signatureX = 10;  // Positionner à gauche
  //     const signatureY = yOffset + 10;  // Positionner juste après le texte "Pour le Client :"
    
  //     doc.text('Signature:', 10, signatureY);
  //     doc.addImage(signatureDataURL, 'PNG', signatureX, signatureY + 10, signatureWidth, signatureHeight);
    
  //     // Enregistrer le PDF
  //     doc.save('contrat BFI BANK.pdf');
  //   }
  // }

  import { Component, OnInit } from '@angular/core';
  import jsPDF from 'jspdf';
  import { YoussefComponent } from "../youssef/youssef.component";
  import { HttpClient } from '@angular/common/http';
  import { CommonModule } from '@angular/common';
  
  @Component({
    selector: 'app-signature-en-ligne',
    standalone: true,
    imports: [YoussefComponent, CommonModule],
    templateUrl: './signature-en-ligne.component.html',
    styleUrls: ['./signature-en-ligne.component.css']  // Corrected property name here
  })
  export class SignatureEnLigneComponent implements OnInit {
    private canvas!: HTMLCanvasElement;
    private ctx: CanvasRenderingContext2D | null = null;
  
    clientName: string|null = localStorage.getItem("nom"); 
    clientAddress: string = 'ADRESSE CLIENT';
    signatureImageUrl?: string;
  
    constructor(private http: HttpClient) {}
  
    ngOnInit(): void {
      this.canvas = document.getElementById('signature-pad') as HTMLCanvasElement;
      this.ctx = this.canvas.getContext('2d');
      if (this.ctx) {
        this.initializeCanvas();
      }
      this.getSignature();
    }
  
    initializeCanvas(): void {
      if (!this.ctx) return;
  
      this.ctx.strokeStyle = '#000';
      this.ctx.lineWidth = 2;
      this.ctx.lineCap = 'round';
      this.ctx.lineJoin = 'round';
  
      let isDrawing = false;
  
      this.canvas.addEventListener('mousedown', () => isDrawing = true);
      this.canvas.addEventListener('mouseup', () => isDrawing = false);
      this.canvas.addEventListener('mousemove', (event) => {
        if (isDrawing && this.ctx) {
          const rect = this.canvas.getBoundingClientRect();
          const x = event.clientX - rect.left;
          const y = event.clientY - rect.top;
          this.draw(x, y);
        }
      });
  
      this.canvas.addEventListener('mouseout', () => isDrawing = false);
    }
  
    draw(x: number, y: number): void {
      if (!this.ctx) return;
  
      this.ctx.lineTo(x, y);
      this.ctx.stroke();
      this.ctx.beginPath();
      this.ctx.moveTo(x, y);
    }
  
    clearSignature(): void {
      if (!this.ctx || !this.canvas) return;
  
      this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
    }
  
    // saveSignature(): void {
    //   if (!this.canvas) return;
  
    //   const signatureDataUrl = this.canvas.toDataURL('image/png');
    //   const compteId = localStorage.getItem("idCompte"); // Ensure this is the correct ID
  
    //   this.http.post(`http://localhost:8999/api/v1/Account/comptes/${compteId}/save-signature`, { signature: signatureDataUrl })
    //     .subscribe(response => {
    //       console.log('Signature saved successfully!', response);
    //       this.generatePDF(signatureDataUrl);
    //       this.getSignature(); // Refresh the signature after saving
    //     }, error => {
    //       console.error('Error saving signature:', error);
    //     });
    // }
  
    saveSignature(): void {
      if (!this.canvas) return;
    
      const signatureDataUrl = this.canvas.toDataURL('image/png');
      const compteId = localStorage.getItem("idCompte"); // Ensure this is the correct ID
    
      if (!compteId) {
        console.error('No compteId found in localStorage');
        return;
      }
    
      // Step 1: Set contract signature to true
      this.http.put(`http://localhost:8999/api/v1/Account/comptes/${compteId}/sign-contract`, {})
        .subscribe(
          res => {
            console.log('Contract signature set to true successfully!', res);
    
            // Step 2: Save the signature
            this.http.post(`http://localhost:8999/api/v1/Account/comptes/${compteId}/save-signature`, { signature: signatureDataUrl })
              .subscribe(
                response => {
                  console.log('Signature saved successfully!', response);
                  this.generatePDF(signatureDataUrl);
                  this.getSignature(); // Refresh the signature after saving
                },
                error => {
                  console.error('Error saving signature:', error);
                }
              );
          },
          error => {
            console.error('Error setting contract signature:', error);
          }
        );
    }

    getSignature(): void {
      const compteId = localStorage.getItem("idCompte"); // Ensure this is the correct ID
  
      this.http.get<{ signature: string }>(`http://localhost:8999/api/v1/Account/comptes/${compteId}/signature`)
        .subscribe(response => {
          this.signatureImageUrl = response.signature; // Assign the base64 string to be used as an image source
        }, error => {
          console.error('Error retrieving signature:', error);
        });
    }
  
    generatePDF(signatureDataURL: string): void {
      const doc = new jsPDF();
      const lineHeight = 10;  // Hauteur de ligne ajustée
      const pageWidth = doc.internal.pageSize.width;
      const pageHeight = doc.internal.pageSize.height;
      let yOffset = 10;
    
      // Fonction pour ajouter le texte en le divisant en lignes si nécessaire
      const addText = (text: string, isHeader: boolean = false) => {
        doc.setFontSize(isHeader ? 20 : 12);  // Taille de police
        const lines = doc.splitTextToSize(text, pageWidth - 20);
        lines.forEach((line: string) => {
          if (yOffset + lineHeight > pageHeight - 60) {  // Laisser de l'espace pour la signature
            doc.addPage();
            yOffset = 10;  // Réinitialiser la position verticale
          }
          doc.text(line, 10, yOffset);
          yOffset += lineHeight;
        });
      };
    
      // Ajouter le contenu du contrat
      doc.setFont('helvetica', 'normal');
      
      // Ajouter des styles manuellement pour imiter le CSS
      doc.setFontSize(30);
      doc.text('Contrat', 10, yOffset);
      yOffset += 15;
    
      doc.setFontSize(12);
      addText(`Entre : BFI BANK, située à Immeuble BFI, Rue du Lac de Constance, Les Berges du Lac, 1053 Tunis, Tunisie.`);
      addText(`Et : ${this.clientName}.`);
      addText('Objet du Contrat', true);
      addText('Le présent contrat a pour objet de définir les termes et conditions sous lesquels l\'Entreprise fournira au Client les services suivants :');
      addText('Service 1: Ouverture de Compte Bancaire - Assistance complète dans l\'ouverture d\'un nouveau compte bancaire, incluant la collecte et la vérification des documents nécessaires, la configuration des options de compte selon les besoins du client, et la fourniture d\'un accès sécurisé aux services bancaires en ligne.');
      addText('Service 2: Gestion de Compte et Transactions - Gestion quotidienne du compte bancaire, comprenant le traitement des transactions, le suivi des soldes, la gestion des virements et paiements, ainsi que la fourniture de relevés de compte réguliers. Ce service inclut également la gestion des autorisations et des alertes de sécurité.');
      addText('Service 3: Conseils Financiers et Assistance - Fourniture de conseils financiers personnalisés pour aider le client à optimiser la gestion de son compte bancaire. Cela comprend des recommandations sur les produits financiers adaptés, des stratégies d\'épargne et d\'investissement, ainsi qu\'une assistance en cas de problème ou de questionnement concernant le compte.');
      addText('Durée du Contrat', true);
      addText('Le présent contrat entre en vigueur à compter de la date de signature et se terminera le 20/08/2030.');
      addText('Obligations des Parties', true);
      addText('Obligations de l\'Entreprise :');
      addText('1. Fournir des services bancaires conformes aux termes du contrat, incluant l\'ouverture et la gestion du compte bancaire du Client.');
      addText('2. Assurer la confidentialité des informations financières et personnelles du Client.');
      addText('3. Informer le Client de tout changement concernant les frais, les conditions ou les services proposés.');
      addText('Obligations du Client :');
      addText('1. Fournir des informations exactes et complètes lors de l\'ouverture du compte bancaire.');
      addText('2. Respecter les termes et conditions du contrat ainsi que les règlements internes de l\'Entreprise.');
      addText('Confidentialité', true);
      addText('Les Parties s\'engagent à maintenir la confidentialité des informations échangées dans le cadre du présent contrat.');
      addText('Résiliation', true);
      addText('Le contrat peut être résilié par l\'une ou l\'autre des Parties sous réserve de respecter un préavis de 60 jours.');
      addText('Litiges', true);
      addText('En cas de litige relatif au présent contrat, les Parties s\'engagent à tenter de résoudre le différend à l\'amiable avant de saisir les tribunaux compétents.');
      addText('Signatures', true);
      const today = new Date();
      const formattedDate = today.toLocaleDateString('fr-FR', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
      });
      
      addText(`Fait le ${formattedDate}`);
      addText('Pour le Client :');
        
      // Ajouter la signature au PDF
      const signatureWidth = 50;
      const signatureHeight = 50;
      const signatureX = 10;  // Positionner à gauche
      const signatureY = yOffset + 10;  // Positionner juste après le texte "Pour le Client :"
    
      doc.text('Signature:', 10, signatureY);
      doc.addImage(signatureDataURL, 'PNG', signatureX, signatureY + 10, signatureWidth, signatureHeight);
    
      // Enregistrer le PDF
      doc.save('contrat_BFI_BANK.pdf'); // Added underscore to file name for clarity
    }
  }
  