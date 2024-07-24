import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OuvrirCompteComponent } from './ouvrir-compte.component';

describe('OuvrirCompteComponent', () => {
  let component: OuvrirCompteComponent;
  let fixture: ComponentFixture<OuvrirCompteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OuvrirCompteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OuvrirCompteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
