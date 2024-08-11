import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarteBancaireComponent } from './carte-bancaire.component';

describe('CarteBancaireComponent', () => {
  let component: CarteBancaireComponent;
  let fixture: ComponentFixture<CarteBancaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarteBancaireComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CarteBancaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
