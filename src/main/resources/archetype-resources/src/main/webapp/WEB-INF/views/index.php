<?php get_header(); ?>

<?php/* include(TEMPLATEPATH.'/carousel.php'); */?>

<section class="portada">
	<div class="container">
		<?php query_posts( array ( 'category_name' => 'portada_index', 'posts_per_page' => 1, 'order' => 'ASC' ) );
			if ( have_posts() ) : ?>
				<?php /* Start the Loop */ ?>
				<?php while ( have_posts() ) : the_post(); ?>
					<div data-sr="enter right" class="hidden-xs">
						<?php the_post_thumbnail(); ?>
					</div>
					<div class="row">
						<div data-sr="enter left, over 0.5s" class="col-xs-12 col-sm-7">
							<h1><?php the_title() ?></h1>
							<br/>

							<h4><?php the_content()?></h4>
							<br/>
							<button class="btn btn-primary btn-lg" type="submit" id="goToProducts">NUESTROS PRODUCTOS</button>
						</div>
					</div>
				<?php endwhile; ?>
			<?php else : ?>
				<h1>[NO HAY POST]</h1>
			<?php endif; // end have_posts() check ?>
		<?php wp_reset_query();?>
	</div>
</section>

<!--<section class="pasos">
	<div class="container-pasos">
		<div class="row">
			<div class="col-xs-12">
				<div class="row">
					<div class="col-xs-12">
						<?php query_posts( array ( 'category_name' => 'pasos_title_index', 'posts_per_page' => 1, 'order' => 'ASC' ) );
							if ( have_posts() ) : ?>
								<?php /* Start the Loop */ ?>
								<?php while ( have_posts() ) : the_post(); ?>
									<h3 class="text-center title-section"><?php the_title() ?></h3>
								<?php endwhile; ?>
							<?php endif; // end have_posts() check ?>
						<?php wp_reset_query();?>
						<br/>
					</div>
				</div>
				<div class="row portada-pasos">
					<?php query_posts( array ( 'category_name' => 'pasos_index', 'posts_per_page' => 4, 'order' => 'ASC' ) );
						if ( have_posts() ) : ?>
							
							<?php /* Start the Loop */ ?>
							<?php while ( have_posts() ) : the_post(); ?>
								<div class="col-xs-12 col-sm-3 text-center">
									<?php if ( has_post_thumbnail() ) :?>
										<?php the_post_thumbnail( array(150,150) ); ?>
									<?php endif;?>
									<h4><?php the_title();?></h4>
								</div>
							<?php endwhile; ?>

						<?php else : ?>
							<h1>[NO HAY POST]</h1>
						<?php endif; // end have_posts() check ?>
					<?php wp_reset_query();?>
				</div>
			</div>
		</div>
	</div>
</section>-->



<section class="productos">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<?php query_posts( array ( 'category_name' => 'productos_title_index', 'posts_per_page' => 1, 'order' => 'ASC' ) );
					if ( have_posts() ) : ?>
						<?php /* Start the Loop */ ?>
						<?php while ( have_posts() ) : the_post(); ?>
							<h3 data-sr="enter top, over 0.5s" class="text-center title-section"><?php the_title() ?></h3>
						<?php endwhile; ?>
					<?php endif; // end have_posts() check ?>
				<?php wp_reset_query();?>
				<br/>
			</div>
		</div>
		<div class="row">
			
			<?php 
			$array_productos = get_posts( array( 'category_name' => 'productos_index', 'posts_per_page' => 3, 'order' => 'ASC' ) );
			$productos = array();
			foreach ($array_productos as $producto) {
				$productos[] += $producto->ID;
			}?>
			<?php if(!empty($productos[0])) : ?>
				<div data-sr="enter top, over 1s" class="col-xs-12 col-sm-4 text-center">
					<div class="producto-index">
						<div class="producto-images-box">
							<?php if (class_exists('MultiPostThumbnails')) :
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-1',$post_id=$productos[0],'part1-1');
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-2',$post_id=$productos[0],'part1-2');
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-3',$post_id=$productos[0],'part1-3');
							endif; ?>
							<a href="<?php echo esc_url(get_permalink(get_page_by_title('Convenio Marco')));?>" class="btn btn-primary btn-md text-uppercase" role="button"><?php echo get_the_title($productos[0])?></a>
						</div>
					</div>
				</div>
			<?php endif?>

			<?php if(!empty($productos[1])) : ?>
				<div data-sr="enter top, over 1s" class="col-xs-12 col-sm-4 text-center">
					<div class="producto-index">
						<div class="producto-images-box">
							<?php if (class_exists('MultiPostThumbnails')) :
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-1',$post_id=$productos[1],'part2-1');
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-2',$post_id=$productos[1],'part2-2');
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-3',$post_id=$productos[1],'part2-3');
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-3',$post_id=$productos[1],'part2-4');
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-3',$post_id=$productos[1],'part2-5');
							endif; ?>
							<a href="<?php echo esc_url(get_permalink(get_page_by_title('Soluciones de Firma Electrónica')));?>" class="btn btn-primary btn-md text-uppercase" role="button"><?php echo get_the_title($productos[1])?></a>
						</div>
					</div>
				</div>
			<?php endif?>

			<?php if(!empty($productos[2])) : ?>
				<div data-sr="enter top, over 1s" class="col-xs-12 col-sm-4 text-center">
					<div class="producto-index">
						<div class="producto-images-box">
							<?php if (class_exists('MultiPostThumbnails')) :
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-1',$post_id=$productos[2],'part3-1');
								MultiPostThumbnails::the_post_thumbnail(get_post_type(),'image-2',$post_id=$productos[2],'part3-2');
							endif; ?>
							<a class="btn btn-warning btn-md text-uppercase" role="button"><?php echo get_the_title($productos[2])?></a>
						</div>
					</div>
				</div>
			<?php endif?>

			
		</div>
	</div>
</section>

<!--<section class="clientes" id="clientes">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h3 class="text-center title-section">Nuestros clientes</h3>
			</div>
		</div>
	</div>
</section>-->


<!--<?php /*PRODUCTOS */ ?>
<?php query_posts( array ( 'category_name' => 'productos_index', 'posts_per_page' => 3, 'order' => 'ASC' ) );
	if ( have_posts() ) : ?>
		<div class="container" id="productos">
			<div class="row">
				<?php /* Start the Loop */ ?>
				<?php while ( have_posts() ) : the_post(); ?>
					<div class="col-xs-12 col-md-4 well">
						<h2><?php the_title();?></h2>
						<p><?php the_content();?></p>
						<p><button type="button" class="btn btn-default">Leer más</button></p>
					</div>
				<?php endwhile; ?>
			</div>
		</div>
	<?php else : ?>
		<h1>[NO HAY POST]</h1>
	<?php endif; // end have_posts() check ?>
<?php wp_reset_query();?>
	
<?php /*CARACTERISTICAS */ ?>
<?php $cont=1;?>
<?php query_posts( array ( 'category_name' => 'caracteristicas_index', 'posts_per_page' => 3, 'order' => 'ASC' ) );
	if ( have_posts() ) : ?>
		<div class="container" id="caracteristicas">
			<?php /* Start the Loop */ ?>
			<?php while ( have_posts() ) : the_post(); ?>
				<div class="row">
					<?php if( $cont%2!=0 ) : ?>
						<div class="col-xs-12 col-md-3 text-center well">				
					<?php else: ?>
						<div class="col-xs-12 col-md-3 col-md-push-9 text-center well">
					<?php endif?>
						<img src="http://lorempixel.com/150/150" alt="..." class="img-circle">
					</div>
					<div class="col-xs-12 col-md-6 well">
						<h2><?php the_title();?></h2>
						<p><?php the_content();?></p>
					</div>
				</div>
				<?php $cont++;?>
			<?php endwhile; ?>
		</div>
	<?php else : ?>
		<h1>[NO HAY POST]</h1>
	<?php endif; // end have_posts() check ?>
<?php wp_reset_query();?>-->


<?php get_footer(); ?>

