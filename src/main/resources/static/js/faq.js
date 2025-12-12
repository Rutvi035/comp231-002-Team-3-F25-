// FAQ Accordion Functionality
document.addEventListener('DOMContentLoaded', function() {
    
    // Toggle FAQ items
    const faqQuestions = document.querySelectorAll('.faq-question');
    
    faqQuestions.forEach(question => {
        question.addEventListener('click', function() {
            const faqItem = this.parentElement;
            const isActive = faqItem.classList.contains('active');
            
            // Close all other FAQ items
            document.querySelectorAll('.faq-item').forEach(item => {
                item.classList.remove('active');
            });
            
            // Toggle current item
            if (!isActive) {
                faqItem.classList.add('active');
            }
        });
    });
    
    // Category Filter
    const categoryPills = document.querySelectorAll('.category-pill');
    const categorySections = document.querySelectorAll('.faq-category-section');
    
    categoryPills.forEach(pill => {
        pill.addEventListener('click', function() {
            const category = this.getAttribute('data-category');
            
            // Update active pill
            categoryPills.forEach(p => p.classList.remove('active'));
            this.classList.add('active');
            
            // Filter sections
            if (category === 'all') {
                categorySections.forEach(section => {
                    section.classList.remove('hidden');
                });
            } else {
                categorySections.forEach(section => {
                    if (section.getAttribute('data-category') === category) {
                        section.classList.remove('hidden');
                    } else {
                        section.classList.add('hidden');
                    }
                });
            }
            
            // Close all open FAQ items
            document.querySelectorAll('.faq-item').forEach(item => {
                item.classList.remove('active');
            });
        });
    });
    
    // Search Functionality
    const searchInput = document.getElementById('faqSearch');
    const faqItems = document.querySelectorAll('.faq-item');
    
    searchInput.addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        
        if (searchTerm === '') {
            // Show all items if search is empty
            faqItems.forEach(item => {
                item.classList.remove('hidden');
            });
            categorySections.forEach(section => {
                section.classList.remove('hidden');
            });
            return;
        }
        
        // Search through FAQ items
        let hasResults = false;
        
        categorySections.forEach(section => {
            let sectionHasResults = false;
            const itemsInSection = section.querySelectorAll('.faq-item');
            
            itemsInSection.forEach(item => {
                const question = item.querySelector('.faq-question span').textContent.toLowerCase();
                const answer = item.querySelector('.faq-answer p').textContent.toLowerCase();
                
                if (question.includes(searchTerm) || answer.includes(searchTerm)) {
                    item.classList.remove('hidden');
                    sectionHasResults = true;
                    hasResults = true;
                } else {
                    item.classList.add('hidden');
                }
            });
            
            // Show/hide section based on results
            if (sectionHasResults) {
                section.classList.remove('hidden');
            } else {
                section.classList.add('hidden');
            }
        });
        
        // Optional: Show "no results" message
        if (!hasResults) {
            console.log('No results found for: ' + searchTerm);
        }
    });
    
    // Smooth scroll to section when category is selected
    categoryPills.forEach(pill => {
        pill.addEventListener('click', function() {
            const category = this.getAttribute('data-category');
            if (category !== 'all') {
                const targetSection = document.querySelector(`[data-category="${category}"]`);
                if (targetSection) {
                    setTimeout(() => {
                        targetSection.scrollIntoView({ behavior: 'smooth', block: 'start' });
                    }, 100);
                }
            }
        });
    });
});