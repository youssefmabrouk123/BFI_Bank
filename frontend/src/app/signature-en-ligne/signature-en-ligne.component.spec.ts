import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignatureEnLigneComponent } from './signature-en-ligne.component';

describe('SignatureEnLigneComponent', () => {
  let component: SignatureEnLigneComponent;
  let fixture: ComponentFixture<SignatureEnLigneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SignatureEnLigneComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SignatureEnLigneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
