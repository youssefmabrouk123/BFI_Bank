import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZainebComponent } from './zaineb.component';

describe('ZainebComponent', () => {
  let component: ZainebComponent;
  let fixture: ComponentFixture<ZainebComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZainebComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ZainebComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
