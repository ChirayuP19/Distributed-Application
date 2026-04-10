-- Electronics
UPDATE products
SET category_id = (SELECT id FROM categories WHERE name = 'Electronics')
WHERE category_id IS NULL
  AND (
    name ILIKE '%smart%' OR
    name ILIKE '%laptop%' OR
    name ILIKE '%tablet%' OR
    name ILIKE '%camera%' OR
    name ILIKE '%speaker%'
    );

-- Fashion
UPDATE products
SET category_id = (SELECT id FROM categories WHERE name = 'Fashion')
WHERE category_id IS NULL
  AND (
    name ILIKE '%shoe%' OR
    name ILIKE '%sneaker%' OR
    name ILIKE '%nike%' OR
    name ILIKE '%adidas%'
    );

-- Home
UPDATE products
SET category_id = (SELECT id FROM categories WHERE name = 'Home')
WHERE category_id IS NULL
  AND (
    name ILIKE '%table%' OR
    name ILIKE '%chair%' OR
    name ILIKE '%sofa%'
    );