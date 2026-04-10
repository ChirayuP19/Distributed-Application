UPDATE products
SET category_id = (
    SELECT id FROM categories WHERE name = 'Electronics'
)
WHERE category_id IS NULL
  AND (
    name ILIKE '%phone%' OR
    name ILIKE '%laptop%' OR
    name ILIKE '%tablet%' OR
    name ILIKE '%camera%' OR
    name ILIKE '%speaker%' OR
    name ILIKE '%headphones%' OR
    name ILIKE '%watch%' OR
    name ILIKE '%mouse%' OR
    name ILIKE '%keyboard%' OR
    name ILIKE '%usb%'
    );

UPDATE products
SET category_id = (
    SELECT id FROM categories WHERE name = 'Fashion'
)
WHERE category_id IS NULL
  AND (
    name ILIKE '%shoes%' OR
    name ILIKE '%nike%' OR
    name ILIKE '%adidas%' OR
    name ILIKE '%puma%'
    );
UPDATE products
SET category_id = (
    SELECT id FROM categories WHERE name = 'Sports'
)
WHERE category_id IS NULL
  AND (
    name ILIKE '%sports%'
    );
UPDATE products
SET category_id = (
    SELECT id FROM categories WHERE name = 'Home'
)
WHERE category_id IS NULL
  AND (
    name ILIKE '%home%'
    );
UPDATE products
SET category_id = (
    SELECT id FROM categories WHERE name = 'Books'
)
WHERE category_id IS NULL
  AND (
    name ILIKE '%book%'
    );
UPDATE products
SET category_id = (
    SELECT id FROM categories WHERE name = 'Electronics'
)
WHERE category_id IS NULL;
ALTER TABLE products
    ALTER COLUMN category_id SET NOT NULL;